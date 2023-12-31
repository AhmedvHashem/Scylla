// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import memcached from "@/lib/memcached";

export default function handler(req, res) {
  memcached.set("hello", "world", { expires: 5 }, function (err, val) {
    console.log("set", err, val);
  });
  memcached.get("hello", function (err, val) {
    console.log("get", err, val?.toString());
  });

  res.status(200).json({ name: "John Doe" });
}
