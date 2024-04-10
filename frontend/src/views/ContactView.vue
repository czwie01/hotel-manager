<template>
  <div class="contact-view">
    <div class="contact-card">
      <h1>Contact Us</h1>
      <div class="contact-info">
        <p>
          If you have any questions or just want to say hello, feel free to
          reach out to us!
        </p>
        <div class="info-item">
          <img src="@/assets/icons/location.svg" alt="Location" class="icon" />
          <p>Straße der Einheit 123, 12345 Musterstadt</p>
        </div>
        <div class="info-item">
          <img src="@/assets/icons/phone.svg" alt="Phone" class="icon" />
          <p>+49 123 4567890</p>
        </div>
        <div class="info-item">
          <img src="@/assets/icons/email.svg" alt="Email" class="icon" />
          <p>contact@exxelentnights.de</p>
        </div>
        <div class="info-item">
          <img
            src="@/assets/icons/clock.svg"
            alt="Opening Hours"
            class="icon"
          />
          <p>
            Mon - Fri: 9:00 AM - 10:00 PM<br />Sat - Sun: 10:00 AM - 11:00 PM
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "ContactView",
});
</script>

<style lang="scss" scoped>
.contact-view {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: var(--clr-light);
}

.contact-card {
  background: var(--clr-accent);
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 2, 0.3);
  overflow: hidden;
  width: 350px;
  text-align: center;
  padding: 20px;
  margin: 20px;

  h1 {
    color: var(--clr-light);
    margin-bottom: 10px;
  }

  .contact-info {
    margin: 20px 0;

    .info-item {
      display: flex;
      align-items: center;
      margin-bottom: 15px;

      img {
        width: 45px;
        margin-right: 1rem;
      }

      p {
        margin: 0;
        color: var(--clr-light);
        text-align: left;
      }
    }
  }
}
.icon {
  filter: invert(1);
}

p {
  color: var(--clr-light);
}

h2 {
  color: var(--clr-light);
}
</style>
