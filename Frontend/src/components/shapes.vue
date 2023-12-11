<template>
    <div>
    <v-stage ref="stage" :config="stageSize">
    <v-layer>
    <!-- <v-rect :config="{
        x: 20,
        y: 50,
        width: 100,
        height: 100,
        fill: 'red',
        shadowBlur: 10
        }"
    /> -->
    <v-circle :config="{
        x: 200,
        y: 100,
        radius: 50,
        fill: 'green'
        }"
    />
    </v-layer>
    <v-layer ref="dragLayer"></v-layer>
    </v-stage>
</div>
</template>

<script>
import { shapes } from 'konva/lib/Shape';
import { Stage, Layer, Rect } from 'vue-konva';
const width = window.innerWidth;
const height = window.innerHeight;
export default {
name: shapes,
data() {
    return {
    stageSize: {
        width: width,
        height: height
    }
    };
}
};
</script>