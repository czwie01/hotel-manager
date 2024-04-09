<template>
  <footer class="app-footer">
    <div class="footer-content">
      <span class="footer-content__trademark-text"
        >eXXelent Nights! | 2024
      </span>
      <div class="footer-content__links">
        <router-link
          to="/main/impressum"
          class="footer-content__links__impressum"
          >IMPRESSUM</router-link
        >
        <router-link to="/main/contact" class="footer-content__links__contact"
          >KONTAKT</router-link
        >
      </div>
    </div>
  </footer>
</template>

<script lang="ts">
export default {
  name: "AppFooter",
};
</script>

<style lang="scss" scoped>
.app-footer {
  background-color: var(--clr-accent);
  .footer-content {
    display: flex;
    justify-content: space-between;
    align-items: center;
    flex-direction: row;
    flex-wrap: wrap;
    padding: 1rem;
    margin: 0 auto;
    text-decoration: none;
    color: var(--clr-light);
    max-width: 90vw;
    &__trademark-text {
      white-space: nowrap;
    }
    &__links {
      display: flex;
      flex-direction: row;
      flex-wrap: nowrap;
      gap: 2.5rem;
      justify-content: space-between;
      text-decoration: none;

      :hover {
        color: var(--clr-light);
        text-decoration: underline;
      }
    }
  }
}

.footer-link:hover {
  color: var(--clr-dark);
  text-decoration: underline;
}
</style>
