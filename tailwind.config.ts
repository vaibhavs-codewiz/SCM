import { type Config } from "tailwindcss";

const config: Config = {
  content: [
    "./src/main/resources/templates/**/*.html",
    "./src/main/resources/static/**/*.html",
    "./src/main/resources/static/**/*.js",
  ], // Ensure Thymeleaf templates are scanned
  theme: {
    extend: {},
  },
  plugins: [],
};

export default config;
