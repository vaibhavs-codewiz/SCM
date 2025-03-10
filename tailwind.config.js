const Config = {
  content: [
    "./src/main/resources/templates/**/*.html",
    "./src/main/resources/static/**/*.html",
    "./src/main/resources/static/**/*.js",
  ], // Ensure Thymeleaf templates are scanned
  theme: {
    extend: {},
  },
  plugins: [],
  darkMode: "selector",
};

export default config;
