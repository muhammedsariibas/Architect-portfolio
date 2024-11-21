<script setup>
import TheWelcome from '@/components/TheWelcome.vue'
import WelcomeBanner from '@/components/WelcomeBanner.vue'
import About from '../components/About.vue';
import Portfolio from '../components/Portfolio.vue';
import Contact from '../components/Contact.vue';
import Footer from '../components/Footer.vue';
import Offer from '../components/Offer.vue';
</script>

<template>
  <main>
    <section id="welcome">
      <WelcomeBanner/>
    </section>
    <section id="offer">
      <Offer/>
    </section>
    <section id="about">
      <About/>
    </section>
    <section id="portfolio">
      <Portfolio/>
    </section>
    <section id="contact">
      <Contact/>
    </section>
    <Footer/>
  </main>
</template>

<style scoped>
main{
  background-color: rgb(220, 220, 228);
}
</style>