var memjs = require("memjs");

var client = memjs.Client.create("localhost:11211", {
  username: "memcache",
  password: "memcache",
});

client.set("hello", "world", { expires: 5 }, function (err, val) {
  console.log("set", err, val);
});
client.get("hello", function (err, val) {
  console.log("get", err, val?.toString());
});
