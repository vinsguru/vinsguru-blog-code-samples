package com.vinsguru.saga.service.steps;

import com.vinsguru.dto.InventoryRequestDTO;
import com.vinsguru.dto.InventoryResponseDTO;
import com.vinsguru.enums.InventoryStatus;
import com.vinsguru.saga.service.WorkflowStep;
import com.vinsguru.saga.service.WorkflowStepStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class InventoryStep implements WorkflowStep {

    private final WebClient webClient;
    private final InventoryRequestDTO requestDTO;
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    public InventoryStep(WebClient webClient, InventoryRequestDTO requestDTO) {
        this.webClient = webClient;
        this.requestDTO = requestDTO;
    }

    @Override
    public WorkflowStepStatus getStatus() {
        return this.stepStatus;
    }

    @Override
    public Mono<Boolean> process() {
        return this.webClient
                .post()
                .uri("/inventory/deduct")
                .body(BodyInserters.fromValue(this.requestDTO))
                .retrieve()
                .bodyToMono(InventoryResponseDTO.class)
                .map(r -> r.getStatus().equals(InventoryStatus.AVAILABLE))
                .doOnNext(b -> this.stepStatus = b ? WorkflowStepStatus.COMPLETE : WorkflowStepStatus.FAILED);
    }

    @Override
    public Mono<Boolean> revert() {
        return this.webClient
                    .post()
                    .uri("/inventory/add")
                    .body(BodyInserters.fromValue(this.requestDTO))
                    .retrieve()
                    .bodyToMono(Void.class)
                    .map(r ->true)
                    .onErrorReturn(false);
    }
}
