<template>
  <div class="impressum">
    <h1>Impressum</h1>
    <p>Verantwortlich für den Inhalt dieser Webseite:</p>

    <h2>eXXelent Nights! GmbH</h2>
    <p>Geschäftsführer: Max Mustermann</p>
    <p>Straße der Einheit 123</p>
    <p>12345 Musterstadt</p>

    <h3>Kontakt</h3>
    <p>Telefon: +49 123 4567890</p>
    <p>Fax: +49 123 4567899</p>
    <p>E-Mail: kontakt@coolbuns.de</p>

    <h3>Registereintrag</h3>
    <p>Eintragung im Handelsregister.</p>
    <p>Registergericht: Amtsgericht Musterstadt</p>
    <p>Registernummer: HRB 123456</p>

    <h3>Umsatzsteuer-ID</h3>
    <p>Umsatzsteuer-Identifikationsnummer gemäß §27 a Umsatzsteuergesetz:</p>
    <p>DE123456789</p>

    <h3>Haftungsausschluss</h3>
    <p>
      Die eXXelent Nights! GmbH übernimmt keine Haftung für die Inhalte externer
      Links, trotz sorgfältiger inhaltlicher Kontrolle. Für den Inhalt der
      verlinkten Seiten sind ausschließlich deren Betreiber verantwortlich.
    </p>

    <p>
      Alle hier verwendeten Namen, Begriffe, Zeichen und Grafiken können Marken-
      oder Warenzeichen im Besitze ihrer rechtlichen Eigentümer sein. Die Rechte
      aller erwähnten und benutzten Marken- und Warenzeichen liegen
      ausschließlich bei deren Besitzern.
    </p>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "ImpressumView",
  setup() {},
});
</script>

<style lang="scss" scoped>
.impressum {
  width: 80vw;
  margin: auto;
  padding: 20px;
}
</style>
