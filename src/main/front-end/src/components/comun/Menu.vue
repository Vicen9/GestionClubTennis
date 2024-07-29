<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <ul class="navbar-nav">

            <li class="nav-item"> <a href="http://localhost:8080/gestionaTennis-1.0-SNAPSHOT/tennis/index.xhtml" class="nav-link">Inicio</a></li>

            <li class="nav-item"><a href="http://localhost:8080/gestionaTennis-1.0-SNAPSHOT/tennis/quienesSomos.xhtml" class="nav-link">Quienes somos</a></li>

            <li class="nav-item"> <a href="http://localhost:8080/gestionaTennis-1.0-SNAPSHOT/tennis/reserva.xhtml" class="nav-link">Reserva</a></li>

        </ul>
    </nav>

</template>

<script>
export default {
    props: [
        'text'
    ],
    mounted() {
        console.log("Cargando componente SFC: Menu.vue")
    }
}

</script>