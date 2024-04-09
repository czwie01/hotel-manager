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

<style scoped>
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
    background-color: linear-gradient(to top, #d50c0c, rgb(206, 224, 248));
  }
}
</style>
