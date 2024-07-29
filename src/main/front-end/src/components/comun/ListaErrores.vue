<template>
  <div v-if="Object.keys(msgs).length > 0">
    <ul>
      <li class="text-danger" v-for="(message,property, index) in msgs" :key="property">
        {{property}}: {{message}}
      </li>
    </ul>
  </div>
</template>

<script>
/* Component definition sample */

export default {
  props: [
    'msgs'  //Component attribute (single binding)
  ],
}
</script>