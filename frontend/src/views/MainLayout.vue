<template>
  <div class="main-layout">
    <AppHeader></AppHeader>
    <main class="content">
      <router-view></router-view>
    </main>

    <AppFooter></AppFooter>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import AppHeader from "@/components/AppHeader.vue";
import AppFooter from "@/components/AppFooter.vue";

export default defineComponent({
  components: {
    AppHeader,
    AppFooter,
  },
});
</script>

<style lang="scss" scoped>
.main-layout {
  display: flex;
  flex-direction: column;
  align-items: stretch;
  min-height: 100vh; /* Full viewport height */
  .content {
    /* display: flex; */
    flex-direction: column;
    flex-wrap: wrap;
    flex-grow: 1;
    justify-content: center;
    align-items: center;
    align-content: center;
    padding: 1rem 1rem 1rem 1rem;
  }
}
.content::after {
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-image: url("@/assets/pictures/homeview-background.jpg");
  background-size: cover;
  background-position: center;
  background-attachment: fixed;
  opacity: 0.2; /* Adjust this value to change the transparency */
  z-index: -2;
}
</style>
