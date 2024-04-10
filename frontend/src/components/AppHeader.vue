<template>
  <header class="app-header">
    <div class="app-header__content">
      <img
        :src="logo"
        alt="eXXelent Nights! Logo"
        class="app-header__content__logo logo"
      />
      <nav class="header__content__nav">
        <Navigation />
      </nav>
    </div>
  </header>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Navigation from "./AppNavigation.vue";
import logo from "@/assets/logos/exxellent-logo.svg";

export default defineComponent({
  components: {
    Navigation,
  },
  setup() {
    return {
      logo,
    };
  },
});
</script>

<style lang="scss" scoped>
.app-header {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-items: center;
  height: 6em;
  background-color: var(--clr-accent);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
  &__content {
    padding: 0rem 2rem 0rem 2rem;
    display: flex;
    flex-direction: row;
    flex-grow: 1;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
    &__logo {
      display: flex;
      align-items: center;
      filter: invert(1);
    }
  }
}

.header-nav {
  display: flex;
  align-items: center;
}

.header-nav a {
  color: #333;
  text-decoration: none;
  margin: 0 1rem;
  font-weight: bold;
  font-size: 1rem;
}

.order-summary-header {
  display: flex;
  align-items: center;
  font-size: 1rem;
  color: #333;
}
</style>
