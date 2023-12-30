const app = require("express")();

const port = process.env.PORT || 8080;

var count = 0;
app.get("/", (req, res) =>
  res.json({ message: "Docker is easy 🐳", count: ++count })
);

app.listen(port, () =>
  console.log(`app listening on http://localhost:${port}`)
);

function main() {
  console.log(process.platform);
}
main();
