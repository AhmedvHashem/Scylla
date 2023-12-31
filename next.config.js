/** @type {import('next').NextConfig} */

// const nextComposePlugins = require("next-compose-plugins");

// function webpackConfig(config) {
//   config.module.rules.forEach((rule) => {});

//   return config;
// }
const nextConfig = {
  // webpack: webpackConfig,
  reactStrictMode: true,
};

// const { withPlugins } = nextComposePlugins.extend(() => ({}));
// const config = withPlugins(nextConfig);

module.exports = nextConfig;
