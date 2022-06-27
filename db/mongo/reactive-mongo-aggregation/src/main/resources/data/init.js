// create db
db = db.getSiblingDB('freelancer');

// create user
db.createUser({
    user: 'freelancer',
    pwd: 'freelancer',
    roles:[
        {
            role: 'readWrite',
            db: 'freelancer'
        }
    ]
});

// create collection
db.createCollection('freelancer');

db.freelancer.insertMany([
    {
        "name": "sam",
        "age": 40,
        "skills": [ "js", "react", "python"]
    },
    {
        "name": "jack",
        "age": 38,
        "skills": [ "js", "angular", "postgres"]
    },
    {
        "name": "james",
        "age": 30,
        "skills": [ "java", "reactor", "mongo"]
    },
    {
        "name": "smith",
        "age": 32,
        "skills": [ "qa", "selenium"]
    }
]);