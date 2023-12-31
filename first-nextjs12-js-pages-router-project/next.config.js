/** @type {import('next').NextConfig} */

function webpackConfig(config) {
  config.experiments = { ...config.experiments, topLevelAwait: true };
  return config;
}
const nextConfig = {
  webpack: webpackConfig,
  reactStrictMode: true,
};

module.exports = nextConfig;
