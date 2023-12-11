<!-- Shape.vue -->
<template>
  <nav>
    <div
      class="shape"
      :style="{ left: shape.left + 'px', top: shape.top + 'px', width: shape.width + 'px', height: shape.height + 'px' , background : maincolor}"
      @mousedown="startDrag"
    >
      <!-- Add any content or styling for your shape -->
      <div class="content">Shape</div>
    </div>
    <div class="colors">
      <!-- <form > -->
        <ul>
          <li>
            <label for="red">RED</label>
            <input type="radio" v-model="maincolor" id="red" value="red" name="color"><br>
          </li>
          <li>
            <label for="green">Green</label>
            <input type="radio" v-model="maincolor" id="green" value="green" name="color"><br>
          </li>
          <li>
            <label for="blue">Blue</label>
            <input type="radio" v-model="maincolor" id="blue" value="blue" name="color"><br>
          </li>
        </ul>
      <!-- </form> -->
    </div>
  </nav>
</template>

<script>
export default {
  name: "helloworld",
  data() {
    return {
      maincolor: "red",
      shape: {
        left: 100,
        top: 100,
        width: 200,
        height: 200,
      },
      isDragging: false,
      startX: 0,
      startY: 0,
    };
  },
  methods: {
    startDrag(e) {
      this.isDragging = true;
      this.startX = e.clientX - this.shape.left;
      this.startY = e.clientY - this.shape.top;
      document.addEventListener('mousemove', this.handleDrag);
      document.addEventListener('mouseup', this.stopDrag);
    },
    handleDrag(e) {
      if (this.isDragging) {
        this.shape.left = e.clientX - this.startX;
        this.shape.top = e.clientY - this.startY;
      }
    },
    stopDrag() {
      this.isDragging = false;
      document.removeEventListener('mousemove', this.handleDrag);
      document.removeEventListener('mouseup', this.stopDrag);
    },
  },
};
</script>

<style scoped>
nav {
  font-size:25px;
  padding: 100px;
}
nav label {
  padding:50px;
  background-color:#EEE;
  margin: 50px;
  border-radius: 50%;
}
nav input {
  width: 50px;
  height: 50px;
  margin: 20px;
  background: red;
  display: none;
}
ul {
  list-style: none;
}
ul li {
  padding:10px;
  width: 50px;
  margin: 20px;
  height: 50px;
}
.shape {
  position: absolute;
  border: 2px solid #333;
  cursor: move;
  background: red;
}
.content {
  padding: 10px;
}
</style>
