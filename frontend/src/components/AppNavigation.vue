<template>
  <nav class="app-navigation">
    <ul>
      <li>
        <router-link to="/main" class="nav-item">Rooms</router-link>
      </li>
    </ul>
  </nav>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "AppNavigation",
  setup() {},
});
</script>

<style scoped>
.app-navigation ul {
  list-style: none;
  padding: 0;
  margin: 0;
  display: flex;
}

.nav-item {
  margin: 1rem; /* Adjust spacing according to your design */
  text-decoration: none;
  color: var(--clr-light); /* Text color, adjust if needed */
  font-weight: bold;
}

.nav-item.router-link-exact-active {
  color: var(--clr-red); /* Active link color, adjust if needed */
}
</style>
