const app = require("express")();

var count = 0;
app.get("/", (req, res) =>
  res.json({ message: "Docker is easy 🐳", count: ++count })
);

const port = process.env.PORT || 8080;

app.listen(port, () =>
  console.log(`app listening on http://localhost:${port}`)
);
