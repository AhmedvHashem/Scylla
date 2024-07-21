const redis = {
    username: "",
    password: "",
    address: "redis",
    port: 6379
}
const credentials = redis.username && redis.password ? `${redis.username}:${redis.password}@` : '';

console.log(credentials);