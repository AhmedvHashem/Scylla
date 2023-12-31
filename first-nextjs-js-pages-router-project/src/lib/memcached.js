const memjs = require("memjs");

const client = memjs.Client.create("localhost:11211", {
  username: "memcache",
  password: "memcache",
});

export default client;
