// =====================================================================
//  Perfectly reasonable behavior. Please ruin it.
// =====================================================================

// Grab every part of the page we need.
const form = document.getElementById("form");
const nameInput = document.getElementById("name");
const phoneInput = document.getElementById("phone");
const ageSlider = document.getElementById("age");
const ageValue = document.getElementById("age-value");
const colorSelect = document.getElementById("color");
const termsBox = document.getElementById("terms");
const submitButton = document.getElementById("submit");
const clearButton = document.getElementById("clear");
const statusText = document.getElementById("status");

// ---------------------------------------------------------------------
//  Live updates
// ---------------------------------------------------------------------
ageSlider.addEventListener("input", () => {
  ageValue.textContent = ageSlider.value;
  // >>> IDEAS: show the age in Roman numerals? Show a wrong number?
});

// ---------------------------------------------------------------------
//  Submit
// ---------------------------------------------------------------------
form.addEventListener("submit", (event) => {
  event.preventDefault(); // stop the page from reloading

  const error = validateForm();
  if (error) {
    setStatus(error, "error");
    return;
  }

  const contact = document.querySelector('input[name="contact"]:checked').value;
  setStatus("Submitted successfully!", "success");
  alert(
    "Submitted!\n" +
    "Name: " + nameInput.value + "\n" +
    "Phone: " + phoneInput.value + "\n" +
    "Age: " + ageSlider.value + "\n" +
    "Favorite color: " + colorSelect.value + "\n" +
    "Contact by: " + contact
  );
  // >>> IDEAS: a fake loading bar first? "Are you REALLY sure?" x10?
});

// ---------------------------------------------------------------------
//  Clear
// ---------------------------------------------------------------------
clearButton.addEventListener("click", () => {
  form.reset();
  ageValue.textContent = ageSlider.value;
  setStatus("Form cleared.");
  // >>> IDEAS: what if Clear... didn't clear? Or cleared one letter per second?
});

// ---------------------------------------------------------------------
//  Rules. Returns an error message, or null if everything is fine.
//  >>> MESS WITH THIS: rulesn't? "Name must contain a vowel and
//      a prime number." Just make sure SOME input can pass.
// ---------------------------------------------------------------------
function validateForm() {
  if (nameInput.value.trim() === "") {
    return "Please enter your name.";
  }
  const digits = phoneInput.value.replace(/[^0-9]/g, "");
  if (digits.length !== 10) {
    return "Phone number must have 10 digits.";
  }
  if (!termsBox.checked) {
    return "You must agree to the terms.";
  }
  return null; // all good
}

// ---------------------------------------------------------------------
//  Help: change the status message at the bottom.
//  type can be "error", "success", or left out.
// ---------------------------------------------------------------------
function setStatus(message, type) {
  statusText.textContent = message;
  statusText.className = "status" + (type ? " " + type : "");
}
