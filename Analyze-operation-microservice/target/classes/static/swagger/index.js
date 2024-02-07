const modal = document.getElementById("loginModal");
const form = document.getElementById("loginForm");

form.onsubmit = function (event) {
  event.preventDefault();
  const { uname, psw } = form.elements;
  handleLogin(uname.value, psw.value);
};

function handleLogin(username, password) {
  if (!isValidCredentials(username, password)) {
    alert("The username or password you entered is incorrect. Access denied.");
    return;
  }

  setLoginState();
  hideModal();
  loadSwaggerUI();
  displayUserDetails(username);
}

function isValidCredentials(username, password) {
  return username === "admin" && password === "admin";
}

function setLoginState() {
  localStorage.setItem("loggedIn", true);
  setTimeout(() => localStorage.removeItem("loggedIn"), 3600000); // timeout after 1 hour
}

function hideModal() {
  modal.style.display = "none";
}

function displayUserDetails(username) {
  document.getElementById("username").textContent = "Username: " + username;
  document.getElementById("property").textContent = "Property: Capgemini";
  ["username", "property", "logoutButton"].forEach(
    (id) => (document.getElementById(id).style.display = "block")
  );
}

// Function to load Swagger UI
function loadSwaggerUI() {
  const defaultApiName = "Spring Boot API";
  fetch("/config")
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok: " + response.statusText);
      }
      return response.json();
    })
    .then((configs) => {
      displayAPINames(configs);

      const queryParams = new URLSearchParams(window.location.search);
      const primaryName = queryParams.get("urls.primaryName") || defaultApiName;

      const ui = SwaggerUIBundle({
        urls: configs.map((api) => ({
          url: api.url,
          name: api.name,
        })),
        dom_id: "#swagger-ui",
        deepLinking: true,
        presets: [SwaggerUIBundle.presets.apis, SwaggerUIStandalonePreset],
        plugins: [SwaggerUIBundle.plugins.DownloadUrl],
        layout: "StandaloneLayout",
        primaryName: primaryName,
      });

      ui.on("complete", function () {
        if (localStorage.getItem("loggedIn")) {
          const topbar = document.querySelector(".topbar");
          if (!topbar.querySelector(".logout-button")) {
            const logoutButton = document.createElement("button");
            logoutButton.innerText = "Logout";
            logoutButton.onclick = logout;
            logoutButton.className = "btn logout-button absolute top-0 right-0";
            topbar.appendChild(logoutButton);
          }
        }
      });
      window.ui = ui;
    })
    .catch(function (error) {
      console.error("Failed to initialize Swagger UI:", error);
    });
}

function displayAPINames(configs) {
  const environment = determineEnvironment(configs);
  document.getElementById("environment").textContent =
    "Environment: " + environment;
}

function determineEnvironment(configs) {
  const apiNames = configs.map((api) => api.name);
  if (apiNames.some((name) => /-DEV1$/.test(name))) {
    return "Development";
  } else if (apiNames.some((name) => /-TESTING$/.test(name))) {
    return "Testing";
  } else if (apiNames.some((name) => /-PROD$/.test(name))) {
    return "Production";
  } else {
    return "Not Specified";
  }
}

window.onload = function () {
  if (localStorage.getItem("loggedIn")) {
    const username = "admin";
    displayUserDetails(username);
    loadSwaggerUI();
    document.getElementById("main").style.display = "block";
    document.getElementById("userDetails").style.display = "block";
    modal.style.display = "none";
  } else {
    modal.style.display = "block";
  }
};

function logout() {
  localStorage.removeItem("loggedIn");
  window.location.reload();
}

document.getElementById("logoutButton").onclick = logout;
