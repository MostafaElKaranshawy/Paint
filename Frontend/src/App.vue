<!-- Shape.vue -->
<template>
  <nav>
    <div class="container">
      <div class="bar">
        <div class="bar1">
          <div class="shapes">
            <ul class="ulstyle">
              <li class="listyle circle shapeli" @click="setshape('circle'), selected('circle')">
                <img src="./assets/icons/circle.svg" alt="Circle">
                <div class="shape">Circle</div>
              </li>
              <li class="listyle square shapeli" @click="setshape('square'), selected('square')">
                <img src="./assets/icons/square.png" alt="Square">
                <div class="shape" >Square</div>
              </li>
              <li class="listyle rect shapeli" @click="setshape('rect') , selected('rect')">
                <img src="./assets/icons/rectangle.png" alt="Rectangle">
                <div class="shape" >Rectangle</div>
              </li>
              <li class="listyle tri shapeli" @click="setshape('tri') , selected('tri')">
                <img src="./assets/icons/triangle.svg" alt="Triangle">
                <div class="shape" >Triangle</div>
              </li>
              <li class="listyle ellipse shapeli" @click="setshape('ellipse') , selected('ellipse')">
                <img src="./assets/icons/ellipse.png" alt="Ellipse">
                <div class="shape" >Ellipse</div>
              </li>
              <li class="listyle line shapeli" @click="setshape('line'), selected('line')">
                <img src="./assets/icons/line.png" alt="Line">
                <div class="shape" >Line</div>
              </li>
            </ul>
          </div>
          <!-- options -->
          <div class="shapes" @click="clearselect">
            <ul class="ulstyle">
              <li class="listyle shapeli fill"  @click="this.colorclicked=!this.colorclicked, this.copyable=false, this.erasable=false,this.showmenu=false , selected('fill'), this.shape=''">
                <img src="./assets/icons/fill.png" alt="Fill">
                <div class="shape">Fill</div>
                </li>
              <li class="listyle shapeli eraser" @click="this.erasable=!this.erasable, this.colorclicked=false, this.copyable=false, this.showmenu=false , selected('eraser'), this.shape=''">
                <img src="./assets/icons/eraser.svg" alt="eraser">
                <div class="shape">Eraser</div>
              </li>
              <li class="listyle clear" @click="clearAll(), this.shape='', resetprops, selected('clear'), commitClear()">
                <img src="./assets/icons/clear.png" alt="clearall">
                <div class="shape" >Clear</div>
              </li>
              <li class="listyle shapeli copy" @click="this.copyable=!this.copyable, this.colorclicked=false, this.erasable=false, this.showmenu = false, selected('copy'), this.shape=''">
                <img src="./assets/icons/copy.png" alt="Copy">
                <div class="shape">Copy</div>
              </li>
              <li class="listyle" @click="this.shape='', resetprops, selected('undo'),  commitUndo(),this.colorclicked=false, this.copyable=false, this.erasable=false,this.showmenu=false">
                <img src="./assets/icons/undo.png" alt="Undo">
                <div class="shape">Undo</div>
              </li>
              <li class="listyle" @click="this.shape='', resetprops, selected('redo'),  commitRedo(),this.colorclicked=false, this.copyable=false, this.erasable=false,this.showmenu=false">
                <img src="./assets/icons/redo.png" alt="Redo">
                <div class="shape">Redo</div>
              </li>
              <li class="listyle show" @click="if(!issaving&&!isloading){showmenu=!showmenu;issaving=false;isloading=false};this.shape='', resetprops , selected('menu'),this.colorclicked=false, this.copyable=false, this.erasable=false">
                <img src="./assets/icons/bars.svg" alt="menu">
                <div class="shape" @click="issaving=false, isloading=false">Menu</div>
                <div class="menu" v-if="showmenu">
                  <div class="save">
                    <div @click="issaving = !issaving, isloading = false" class="savebutton">
                      <img src="./assets/icons/save.png" alt="save" v-if="!issaving">
                      <div v-if="!issaving">Save</div>
                    </div>
                    <div class="file" v-if="issaving">
                      <input type="text" v-model="path" class="input" placeholder="Enter your path">
                      <input type="text" v-model="filename" class="input" placeholder="Enter file name">
                      <label>
                        <input type="radio" v-model="fileextend" value="json"> JSON
                      </label>
                      <label>
                        <input type="radio" v-model="fileextend" value="xml"> XML
                      </label>
                      <img src="./assets/icons/save.png" alt="save">
                      <div class="saved" @click="commitSave(), issaving = false">Save</div>
                    </div>
                  </div>
                  <div class="load">
                    <div @click="isloading = !isloading, issaving = false" class="savebutton">
                      <img src="./assets/icons/load.png" alt="save" v-if="!isloading">
                      <div v-if="!isloading">load</div>
                    </div>
                    <div class="file" v-if="isloading">
                      <input type="text" v-model="loadpath" class="input" placeholder="Enter your file path">
                      <img src="./assets/icons/load.png" alt="save">
                      <div class="saved" @click="commitLoad(), isloading = false">load</div>
                    </div>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </div>
        <div class="bar1">
          <div class="coloring" @click="clearselect">
            <ul class="ulstyle" >
              <div>Colors</div>
              <li v-for="color in colors" :key="color" :class="color" class="color" @click="changeColor(color)"></li>
              <img src=".\assets\icons\gradient.jpeg" alt="gradient" class="colorgradient" @click="handlegradient">
            </ul>
            <input type="color" v-model="color" class="color gradientcolor" @input="changegradient">
          </div>
          <div class="features">
            <div class="saveimg" @click="this.saveStage()">
              <img src="./assets/icons/save.png" alt="save">
              <span>Save As Img</span>
            </div>
            <div class="mode" @click="pinkmode=!pinkmode, changemode()">
              <span class="pinkmode"></span>
              <span class="nopinkmode"></span>
            </div>
          </div>
        </div>
      </div>
      <div class="board" @click="createKonvaShape" ref="parent" @mousemove="mount">
        <!-- <shape/> -->
        <v-stage :config="stageConfigg" class="boarda"
          ref="stage"
          @mousedown="handleStageMouseDown"
          @touchstart="handleStageMouseDown">
          <v-layer ref="layer">
            <v-circle v-for="shapeConfig in circleshapeConfigs" :key="shapeConfig" class="shapeconfig" ref="circle" 
            @transformend="handleTransformEnd" @mouseup="handleposition"
            :config="shapeConfig" @click="updateColor(shapeConfig),eraser(shapeConfig),copy(shapeConfig), this.currentshape = shapeConfig" 
            @mousedown="this.currentshape = shapeConfig"></v-circle>

            <v-regular-polygon v-for="shapeConfig in squareshapeConfigs" :key="shapeConfig" class="shapeconfig" ref="square" 
            @transformend="handleTransformEnd" @mouseup="handleposition"
            :config="shapeConfig" @click="updateColor(shapeConfig),eraser(shapeConfig),copy(shapeConfig), this.currentshape = shapeConfig" 
            @mousedown="this.currentshape = shapeConfig"></v-regular-polygon>

            <v-rect v-for="shapeConfig in rectshapeConfigs" :key="shapeConfig" class="shapeconfig" ref="rect" 
            @transformend="handleTransformEnd" @mouseup="handleposition"
            :config="shapeConfig" @click="updateColor(shapeConfig),eraser(shapeConfig),copy(shapeConfig), this.currentshape = shapeConfig" 
            @mousedown=" this.currentshape = shapeConfig"></v-rect>

            <v-ellipse v-for="shapeConfig in ellipseshapeConfigs" :key="shapeConfig" class="shapeconfig" ref="ellipse" 
            @transformend="handleTransformEnd" @mouseup="handleposition"
            :config="shapeConfig" @click="updateColor(shapeConfig),eraser(shapeConfig),copy(shapeConfig), this.currentshape = shapeConfig" 
            @mousedown="this.currentshape = shapeConfig"></v-ellipse>

            <v-line v-for="shapeConfig in lineshapeConfigs" :key="shapeConfig" class="shapeconfig" ref="line" 
            @transformend="handleTransformEnd" @mouseup="handleposition"
            :config="shapeConfig" @click="updateColor(shapeConfig),eraser(shapeConfig),copy(shapeConfig), this.currentshape = shapeConfig" 
            @mousedown="this.currentshape = shapeConfig"></v-line>

            <v-regular-polygon v-for="shapeConfig in trishapeConfigs" :key="shapeConfig" class="shapeconfig" ref="tri" 
            @transformend="handleTransformEnd" @mouseup="handleposition"
            :config="shapeConfig" @click="updateColor(shapeConfig),eraser(shapeConfig),copy(shapeConfig), this.currentshape = shapeConfig" 
            @mousedown="this.currentshape = shapeConfig"></v-regular-polygon>
            
            <v-transformer ref="transformer"/>
          </v-layer>
        </v-stage>
      </div>
      
    </div>
  </nav>
  <!-- <router-view/> -->
</template>

<script>
import { shapes } from 'konva/lib/Shape';
import { Stage, Layer, Rect , Transformer} from 'vue-konva';
import shape from './components/shape.vue'
export default {
  data() {
    return {
      shapes: [],
      circleshapeConfigs: [],
      squareshapeConfigs: [],
      sqrshapeConfigs: [],
      rectshapeConfigs: [],
      trishapeConfigs: [],
      ellipseshapeConfigs: [],
      lineshapeConfigs: [],
      colors: ['black', 'grey', 'silver', 'white', 'yellow','burlywood','orange', 'lightsalmon', 'pink', 'red',
      'brown', 'purple', 'blueviolet', 'blue', 'lightblue', 'lightgreen', 'yellowgreen', 'green'],
      shape : '',
      color:'white',
      colorclicked: false,
      erasable: false,
      copyable: false,
      stageConfigg: {
        width: 2184,
        height: 1000,
      },
      selectedShapeName: '',
      currentshape : null,
      currentid: 0,
      path: '',
      filename: '',
      fileextend: '',
      loadpath : '',
      issaving: false,
      isloading: false,
      showmenu: false,
      zIndex: 0,
      pinkmode: false,
      Stage : null,
    }
  },
  mounted(){
    const parentRect = this.$refs.parent;
    if(parentRect){
      this.stageConfigg.height = parentRect.offsetHeight;
      this.stageConfigg.width = parentRect.offsetWidth;
    }
    var colors = document.getElementsByClassName("c");
    for(let c = 0; c < this.colors.length; c++){
      let color = this.colors[c];
      document.getElementsByClassName(color)[0].style.backgroundColor = color;
    }
    this.changemode();
    this.$refs.stage.getStage().backgroundColor = "white";
    this.Stage = this.$refs.stage.getStage();
  },
  components: {
    vStage: Stage,
    vLayer: Layer,
    vRect: Rect,
    shapes,
    vTransformer: Transformer,
    shape,
  },
  methods : {
    handlegradient(){
      document.querySelector(".gradientcolor").click();
    },
    changemode(){
      if(this.pinkmode){
        document.querySelector("nav").classList.add("setpinkmode");
        document.querySelector(".pinkmode").style.visibility = "visible";
        document.querySelector(".nopinkmode").style.visibility = "hidden";
      }
      if(!this.pinkmode){
        document.querySelector("nav").classList.remove("setpinkmode");
        document.querySelector(".pinkmode").style.visibility = "hidden";
        document.querySelector(".nopinkmode").style.visibility = "visible";
      }
    },
    mount() {
      const parentRect = this.$refs.parent;
      if(parentRect){
        this.stageConfigg.height = parentRect.offsetHeight;
        this.stageConfigg.width = parentRect.offsetWidth;
      }
    },
    handleposition(e){
      let pos = (this.currentshape.x !== e.target.x() || this.currentshape.y !== e.target.y());
      this.currentshape.x = e.target.x();
      this.currentshape.y = e.target.y();
      if(pos){
        this.commitChange();
      }
    },
    handleTransformEnd(e) {
    // shape is transformed, let us save new attrs back to the node
    // find element in our state
    const rect = this.rectshapeConfigs.find(
          (r) => r.name === this.selectedShapeName
      );
    const square = this.squareshapeConfigs.find(
          (r) => r.name === this.selectedShapeName
      );
    const tri = this.trishapeConfigs.find(
          (r) => r.name === this.selectedShapeName
      );
    const ellipse = this.ellipseshapeConfigs.find(
          (r) => r.name === this.selectedShapeName
      );
    const circle = this.circleshapeConfigs.find(
          (r) => r.name === this.selectedShapeName
      );
    const line = this.lineshapeConfigs.find(
          (r) => r.name === this.selectedShapeName
      );
    // update the state
    if(rect != null){
      rect.x = e.target.x();
      rect.y = e.target.y();
      rect.rotation = e.target.rotation();
      rect.scaleX = e.target.scaleX();
      rect.scaleY = e.target.scaleY();
    }
    if(square!=null){
      square.x = e.target.x();
      square.y = e.target.y();
      square.rotation = e.target.rotation();
      square.scaleX = e.target.scaleX();
      square.scaleY = e.target.scaleY();
    }
    if(tri!=null){
      tri.x = e.target.x();
      tri.y = e.target.y();
      tri.rotation = e.target.rotation();
      tri.scaleX = e.target.scaleX();
      tri.scaleY = e.target.scaleY();
    }
    if(ellipse!=null){
      ellipse.x = e.target.x();
      ellipse.y = e.target.y();
      ellipse.rotation = e.target.rotation();
      ellipse.scaleX = e.target.scaleX();
      ellipse.scaleY = e.target.scaleY();
    }
    if(circle!=null){
      circle.x = e.target.x();
      circle.y = e.target.y();
      circle.rotation = e.target.rotation();
      circle.scaleX = e.target.scaleX();
      circle.scaleY = e.target.scaleY();
    }
    if(line!=null){
      line.x = e.target.x();
      line.y = e.target.y();
      line.rotation = e.target.rotation();
      line.scaleX = e.target.scaleX();
      line.scaleY = e.target.scaleY();
    }
      this.commitChange();
    },
    handleStageMouseDown(e) {
    // clicked on stage - clear selection
    if (e.target === e.target.getStage()) {
        this.selectedShapeName = '';
        this.updateTransformer();
        return;
    }

    // clicked on transformer - do nothing
    const clickedOnTransformer =
      e.target.getParent().className === 'Transformer';
      if (clickedOnTransformer) {
          return;
      }

      // find clicked rect by its name
      const name = e.target.name();
      const rect = this.rectshapeConfigs.find((r) => r.name === name);
      const square = this.squareshapeConfigs.find((r) => r.name === name);
      const tri = this.trishapeConfigs.find((r) => r.name === name);
      const ellipse = this.ellipseshapeConfigs.find((r) => r.name === name);
      const line = this.lineshapeConfigs.find((r) => r.name === name);
      const circle = this.circleshapeConfigs.find((r) => r.name === name);
      if (rect || square || tri || ellipse || line || circle) {
          this.selectedShapeName = name;
      }else {
          this.selectedShapeName = '';
      }
      this.updateTransformer();
    },
    updateTransformer() {
    // here we need to manually attach or detach Transformer node
      const transformerNode = this.$refs.transformer.getNode();
      const stage = transformerNode.getStage();
      const { selectedShapeName } = this;

      const selectedNode = stage.findOne('.' + selectedShapeName);
      // do nothing if selected node is already attached
      if (selectedNode === transformerNode.node()) {
          return;
      }

      if (selectedNode) {
          // attach to another node
          transformerNode.nodes([selectedNode]);
      } else {
          // remove transformer
          transformerNode.nodes([]);
      }
    },
    startDrag(e) {
      this.isDragging = true;
      this.currentshape.scaleX = 
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
    resetprops(){
      this.copyable = false;
      this.colorclicked = false;
      this.erasable = false;
      this.showmenu = false;
      document.getElementsByClassName('copy')[0].classList.remove('selected');
      document.getElementsByClassName('eraser')[0].classList.remove('selected');
      document.getElementsByClassName('fill')[0].style.backgroundColor = 'white';
      document.getElementsByClassName('fill')[0].style.color = '#0013b6';
    },
    setshape(s){
      this.copyable = false;
      this.colorclicked = false;
      this.erasable = false;
      this.showmenu = false;
      this.shape = s;
      document.getElementsByClassName('copy')[0].classList.remove('selected');
      document.getElementsByClassName('eraser')[0].classList.remove('selected');
      document.getElementsByClassName('fill')[0].classList.remove('selected');
      document.getElementsByClassName('fill')[0].style.backgroundColor = 'white';
      document.getElementsByClassName('fill')[0].style.color = '#0013b6';
    },
    handleMouseMove(event) {
      const parentRect = this.$refs.parent.getBoundingClientRect();
      
      // Calculate relative mouse position
      this.relativeMouseX = event.clientX - parentRect.left;
      this.relativeMouseY = event.clientY - parentRect.top;
    },
    createKonvaShape(e) {
      this.showmenu = false;
      
      if(this.shape){
        document.querySelectorAll('.shapeli').forEach(element => {
          element.classList.remove('selected');
        });
      }
      const parentRect = this.$refs.parent.getBoundingClientRect();
      this.left = parentRect.left;
      this.top = parentRect.top;
      if(this.shape === 'rect'){
        const newShapeConfig = {
        name : 'rect'+(this.rectshapeConfigs.length),
        shapeType : 'rect',
        x: e.clientX - this.left,
        y: e.clientY - this.top,
        };
        this.commitCreate(newShapeConfig);
      }
      if(this.shape === 'square'){
        const newShapeConfig = {
        name : 'square'+(this.squareshapeConfigs.length),
        shapeType : 'square',
        x: (e.clientX - this.left),
        y: (e.clientY - this.top),
        };
        this.commitCreate(newShapeConfig);
      }
      if(this.shape === 'circle'){
        const newShapeConfig = {
          name : 'circle'+(this.circleshapeConfigs.length),
          shapeType : 'circle',
          x: e.clientX - this.left,
          y: e.clientY - this.top,
        };
        this.commitCreate(newShapeConfig);
        
      }
      if(this.shape === 'ellipse'){
        const newShapeConfig = {
          name : 'ellipse'+(this.ellipseshapeConfigs.length),
          shapeType: 'ellipse',
          x: e.clientX - this.left,
          y: e.clientY - this.top,
        };
        this.commitCreate(newShapeConfig);
      }
      if(this.shape === 'tri'){
        const newShapeConfig = {
          name : 'tri'+(this.trishapeConfigs.length),
          shapeType: 'tri',
          x: (e.clientX - this.left),
          y: (e.clientY - this.top),
        };
        this.commitCreate(newShapeConfig);
      }
      if(this.shape === 'line'){
        const newShapeConfig = {
          name : 'line'+(this.lineshapeConfigs.length),
          shapeType : 'line',
          x: (e.clientX - this.left),
          y: (e.clientY - this.top)
        };
        this.commitCreate(newShapeConfig);
      }
      this.shape = '';
    },
    clearselect(){
      this.shape = '';
      document.querySelectorAll('.shapeli').forEach(element => {
        element.classList.remove('selected');
      });
      if(this.colorclicked === true){
        document.getElementsByClassName('fill')[0].classList.add('selected');
        document.getElementsByClassName('fill')[0].style.backgroundColor = this.color;
      }
      if(this.copyable === true){
        document.getElementsByClassName('copy')[0].classList.add('selected');
      }
      if(this.erasable === true){
        document.getElementsByClassName('eraser')[0].classList.add('selected');
      }
    },
    selected(classname) {
      document.querySelectorAll('.shapeli').forEach(element => {
        element.classList.remove('selected');
      });
      if((classname === 'clear' || classname === 'undo' || classname === 'redo' || classname === 'menu')){
        this.copyable = false;
        this.colorclicked = false;
        this.erasable = false;
      }
      else{
        document.getElementsByClassName(classname)[0].classList.add('selected');
      }
      if(this.colorclicked === false){
        document.getElementsByClassName('fill')[0].classList.remove('selected');
        document.getElementsByClassName('fill')[0].style.backgroundColor = 'white';
        document.getElementsByClassName('fill')[0].style.color = '#0013b6';
      }
      if(this.copyable === false){
        document.getElementsByClassName('copy')[0].classList.remove('selected');
      }
      if(this.erasable === false){
        document.getElementsByClassName('eraser')[0].classList.remove('selected');
      }
      if(this.colorclicked){
        document.getElementsByClassName('fill')[0].style.backgroundColor = this.color;

      }
    },
    updateColor(shape) {
      this.showmenu = false;
      if(this.colorclicked==true){
        if(shape.shapeType =='line'){
          shape.stroke = this.color;
        }
        else{
          shape.fill = this.color;
        }
        this.commitChange();
      }
    },
    changegradient() {
      if(this.colorclicked){
        document.getElementsByClassName('fill')[0].style.backgroundColor = this.color;
      }
    },
    changeColor(color){
      this.showmenu = false;
      document.querySelectorAll('.color').forEach(element => {
        element.classList.remove('colorset');
      });
      document.getElementsByClassName(color)[0].classList.add('colorset');
      this.color = color;
      if(this.colorclicked){
        document.getElementsByClassName('fill')[0].style.backgroundColor = this.color;
      }
    },
    clearAll(){
      this.showmenu = false;
      this.circleshapeConfigs = [];
      this.squareshapeConfigs = [];
      this.rectshapeConfigs = [];
      this.trishapeConfigs = [];
      this.ellipseshapeConfigs = [];
      this.lineshapeConfigs = [];
      this.copyable = false;
      this.colorclicked = false;
      this.erasable = false;
      this.shapes = [];
    },
    eraser(shape){
      this.showmenu = false;
      if(this.erasable==true){
        shape.visible = false;
        shapes[shape.id]=shape;
        this.commitChange();
      }
    },
    copy(shape2) {
      let shape = JSON.parse(JSON.stringify(shape2));
      this.showmenu = false;
      console.log(this.shape);
      if(this.copyable){
        if(shape.shapeType === 'circle'){
          shape.name = 'circle'+(this.circleshapeConfigs.length);
          this.commitCopy(shape);
        }
        if(shape.shapeType === 'rect'){
          shape.name = 'rect'+(this.rectshapeConfigs.length);
          this.commitCopy(shape);
        }
        if(shape.shapeType === 'tri'){
          shape.name = 'tri'+(this.trishapeConfigs.length);
          this.commitCopy(shape);
        }
        if(shape.shapeType === 'square'){
          shape.name = 'square'+(this.squareshapeConfigs.length);
          this.commitCopy(shape);
        }
        if(shape.shapeType === 'line'){
          shape.name = 'line'+(this.lineshapeConfigs.length);
          this.commitCopy(shape);
        }
        if(shape.shapeType === 'ellipse'){
          shape.name = 'ellipse'+(this.ellipseshapeConfigs.length);
          this.commitCopy(shape);
        }
      }
    },

    /*Back-end Connection*/

    commitClear(){
      fetch('http://localhost:8080/clear',{
        method:'POST',
        headers:{
          'Content-Type':'application/json',
        },
      })
    },
    commitSave(){
      this.path = this.path.replace(/\\/g, '\\\\');
      let finalPath = this.path + '\\\\' + this.filename + '.' + this.fileextend;
      console.log(this.path);
      console.log(this.filename);
      console.log(this.fileextend);
      console.log(finalPath);
      let toBeSent = {
        pathToAccess: finalPath
      }
      if(this.fileextend === 'json'){
        fetch('http://localhost:8080/saveToJson',{
          method:'POST',
          headers:{
            'Content-Type':'application/json',
          },
          body: JSON.stringify(toBeSent),
        })
        .then(response =>{
          let data = response.json();
          return data
        }).then(response =>{
          if(response)alert("Saved Successfully!");
          if(!response)alert("File is Not Saved");
        })
      }
      if(this.fileextend === 'xml'){
        fetch('http://localhost:8080/saveToXML',{
          method:'POST',
          headers:{
            'Content-Type':'application/json',
          },
          body: JSON.stringify(toBeSent),
        }).then(response =>{
          let data = response.json();
          return data
        }).then(response =>{
          if(response)alert("Saved Successfully!");
          if(!response)alert("File is Not Saved");
        })
      }
      
      this.path = '';
      this.filename = '';
      this.fileextend = '';
    },
    commitLoad(){
      let finalPath = this.loadpath.replace(/\\/g, '\\\\').replace(/"/g, '');
      // console.log(finalPath);
      let toBeSent = {
        pathToAccess: finalPath
      };
      let toBeRecieved = {
        system: []
      };
      
      if(finalPath.includes("xml")){
        fetch('http://localhost:8080/loadFromXML',{
          method:'POST',
          headers:{
            'Content-Type':'application/json',
          },
          body: JSON.stringify(toBeSent),
        }).then((response)=>{
          toBeRecieved = response.json();
          // console.log(toBeRecieved);
          return toBeRecieved;
        }).then((response)=>{
          if(!response.system){
            alert("Falied to load the file :(\nThe File may not be existed");
          }
          else{
            this.shapes = response.system;
            this.parseshapes();
            alert("Loaded Successfully!");
          }
        })
        .catch(error=>{
          alert(error +" Falied to load the file :(\nThe File may not be existed");
        })
      }
      else if(finalPath.includes("json")){
        fetch('http://localhost:8080/loadFromJson',{
          method:'POST',
          headers:{
            'Content-Type':'application/json',
          },
          body: JSON.stringify(toBeSent),
        }).then((response)=>{
          toBeRecieved = response.json();
          return toBeRecieved;
        }).then((response)=>{
          if(!response.system){
            alert("Falied to load the file :(\nThe File may not be existed");
          }
          else{
            this.shapes = response.system;
            this.parseshapes();
            alert("Loaded Successfully!");
          }
        })
        .catch(error=>{
          alert(error + " Falied to load the file\nThe File may not be existed");
        })
      }
      else{ 
        alert("Falied to load the file :(\nThe File may not be existed");
      }
      this.path = '';
      this.filename = '';
      this.fileextend = '';
      this.loadpath = '';
    },
    commitChange(){
      let toBeSent= {
        system : this.shapes
      }
      fetch('http://localhost:8080/change',{
        method:'POST',
        headers:{
          'Content-Type':'application/json',
        },
        body: JSON.stringify(toBeSent),
      }).catch((error)=>{
        console.log(error+"error AAAAAAAAAAA");
      });
    },
    commitUndo(){
      let toBeRecieved= {
        system : []
      }
      fetch('http://localhost:8080/undo',{
        method:'POSt',
        headers:{
          'Content-Type':'application/json',
        },
      }).then((response)=>{
        toBeRecieved = response.json();
        return toBeRecieved;
      }).then((response)=>{
        this.shapes=response.system;
        this.parseshapes();
      })
      .catch((error)=>{
        console.log(error+"error AAAAAAAAAAA");
      });
    },
    commitRedo(){
      let toBeRecieved= {
        system : null
      }
      fetch('http://localhost:8080/redo',{
        method:'POST',
        headers:{
          'Content-Type':'application/json',
        },
      }).then((response)=>{
        toBeRecieved = response.json();
        return toBeRecieved;
      }).then((response)=>{
        this.shapes=response.system;
        this.parseshapes();
      }).catch((error)=>{
        console.log(error+"error AAAAAAAAAAA");
      });
    },
    commitCopy(shape){
      let shape2 = null;
      fetch('http://localhost:8080/copy',{
        method:'POST',
        headers:{
          'Content-Type':'application/json',
        },
        body:JSON.stringify(shape),
      }).then((response)=>{
        shape2 = response.json();
        return shape2;
      }).then((response)=>{
        console.log(response);
        this.parseShape(response);
        console.log(response);
      }).catch((error)=>{
        console.log(error+"error AAAAAAAAAAA");
      })
    },
    commitCreate(shape){
      let toBeCreated = {
        name: shape.name,
        shapeType : shape.shapeType,
        x : shape.x,
        y : shape.y,
      }
      let shape2 = null;
      fetch('http://localhost:8080/createShape',{
        method:'POST',
        headers:{
          'Content-Type':'application/json',
        },
        body:JSON.stringify(toBeCreated),
      }).then((response)=>{
        shape2 = response.json();
        return shape2;
      }).then((response)=>{
        this.parseShape(response);
      }).catch((error)=>{
        console.log(error+"error AAAAAAAAAAA");
      })
    
    },
    parseshapes() {
    if(this.shapes != null) {
        this.rectshapeConfigs = this.shapes.filter(shape => shape.shapeType === 'rect');
        this.squareshapeConfigs = this.shapes.filter(shape => shape.shapeType === 'square');
        this.trishapeConfigs = this.shapes.filter(shape => shape.shapeType === 'tri');
        this.ellipseshapeConfigs = this.shapes.filter(shape => shape.shapeType === 'ellipse');
        this.lineshapeConfigs = this.shapes.filter(shape => shape.shapeType === 'line');
        this.circleshapeConfigs = this.shapes.filter(shape => shape.shapeType === 'circle');
      }
    },
    parseShape(shape){
      console.log(shape);
      console.log(shape.name);
      if(shape.shapeType === 'square'){
        shape.rotation = 45;
        this.squareshapeConfigs.push(shape);
      }
      else if(shape.shapeType === 'rect'){
        this.rectshapeConfigs.push(shape);
      }
      else if(shape.shapeType === 'tri'){
        shape.sides = 3;
        this.trishapeConfigs.push(shape);
      }
      else if(shape.shapeType === 'ellipse'){
        this.ellipseshapeConfigs.push(shape);
      }
      else if(shape.shapeType === 'line'){
        shape.points = [0, 0, 200, 0];
        shape.fill = 'black';
        shape.strokeWidth = 5;
        this.lineshapeConfigs.push(shape);
      }
      else if(shape.shapeType === 'circle'){
        this.circleshapeConfigs.push(shape);
      }
      this.shapes.push(shape);
    },
    saveStage() {
      console.log("Hello");
      const dataURL = this.Stage.toDataURL();
      this.downloadURI(dataURL, 'stage.png');
    },
    downloadURI(uri, name) {
      const link = document.createElement('a');
      link.download = name;
      link.href = uri;
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    },
  },
};
</script>


<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.boarda { 
  z-index: 2;
}
ul {
  list-style: none;
}
nav {
  padding: 10px;
  margin: 0;
  background-image: linear-gradient(90deg, #92CBFF, #4680FF);
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.container {
  width: calc(100% - 100px);
}
.bar {
  max-width: 100%;
  margin-top: 2%;
  height: 5%;
  display: flex;
  flex-direction: column;
  flex-flow: column;
  justify-content: center;
  flex-wrap: nowrap;
}
.bar1{
  max-width: 100%;
  display: flex;
  align-items: center;
  flex-wrap: nowrap;
  justify-content: space-between;
  display:relative;
}
.bar .shapes {
  min-width: calc(50% - 10px);
  background:white;
  border-radius: 30px;
  height: 50px;
  padding: 20px auto;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}

.bar .options {
  width: 100%;
  background:white;
  border-radius: 30px;
  height: 50px;
  display: flex;
  flex-direction: row;
  margin-top: 10px;
  justify-content: space-evenly;
  flex-flow: row wrap;
}
.bar .coloring {
  display: flex;
  flex-direction: row;
  background: white;
  height: 40px;
  border-radius: 30px;
  min-width: 50%;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
}
.bar .coloring input {
  overflow: hidden;
  width: 30px;
  height: 30px;
  cursor: pointer;
  margin-right: 20px;
  border: none;
  border-radius: 15px;
  padding: 0;
}
.bar .coloring ul {
  display: flex;
  flex-direction: row;
  flex-flow: row wrap;
  width: 100%;
  justify-content: space-evenly;
  align-items: center;
}
.bar .coloring ul li{
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background-color: black;
  margin: 5px;
  border: #4a4a4a solid 2px;
  cursor: pointer;
}
.options ul li img {
  width: 16%;
  height: 10%;
}
.clear img {
  filter: contrast(0);
}
.ulstyle{
  display: flex;
  justify-content: space-evenly;
  width: calc(100% - 100px);
  font-size: 20px;
  font-weight: 800;
}
.listyle{
  display: flex;
  margin: 10px;
  cursor: pointer;
  color: #0013b6;
  padding: 10px;
}
@media (max-width: 1600px) {
  .listyle {
    margin: 5px;
  }
}
.listyle:hover {
  background-color: #4a4a4a;
  border-radius: 10px;
  width: auto;
  color: white;
}
img {
  width: 25px;
  margin-right: 10px;
}
.board {

  display: block;
  margin-top: 20px;
  width: auto;
  height: calc(90vh - 90px);
  background: white;
  border-radius: 30px;
}
.menu {
  opacity: 1;
  display: flex;
  flex-direction: row;
  width: auto;
  padding: 10px;
  position: absolute;
  background: #7eb8ff;
  color: white;
  border-radius: 20px;
  top: 102%;
  right: 0;
  transform: scale(1.1);
}
.menu div {
  z-index: 200;
  display: flex;
  margin: 10px;
  align-items: center;
  justify-content: center;
  border-bottom: 1px solid transparent;
}
.menu .saved,
.menu .savebutton{
  font-size: 20px;
}
.menu .saved:hover{
  color: #002168;
  cursor: pointer;
}
.menu .savebutton:hover{
  color: #002168;
  cursor: pointer;
}
.menu div img {
  margin-left:10px;
  width: 20px;
  height: 20px;
}
.show  {
  position: relative;
}

@media (max-width: 1800px) {
  * {
    font-size: 20px;
  }
  img {
    width: 15px;
    height: 15px;
  }
  
}
@media (max-width: 1800px) {
  * {
    font-size: 20px;
  }
  img {
    width: 15px;
    height: 15px;
  }
  
}

@media (max-width: 1500px) {
  * {
    font-size: 15px;
  }
  img {
    width: 15px;
    height: 15px;
  }
  .listyle {
    transform: scale(0.88, 0.88);
  }
  
}@media (max-width :1300px) {
  .bar {
    font-size: 25px;
  }
  .bar1 { 
    flex-direction: column;
    width: 100%;
  }
  .shapes {
    width: 100%;
  }
  .shapes:first-child {
    margin-bottom: 10px;
  }
  .coloring{ 
    width: 100%;
  }
}
.selected {
  background-color: #4a4a4a;
  opacity: 0.7;
  border-radius: 10px;
  width: auto;
  color: white;
}
.colorgradient {
  width: 20px;
  height: 20px;
  border-radius: 50%;
  cursor: pointer;
}
.gradientcolor {
  position: absolute;
  right: 50%;
  visibility: hidden;
}
.colorset {
  box-shadow: 0 0 0 2px grey;
}
.shapeconfig{
  cursor: pointer;
}
.input {
  padding: 5px 10px;
  border-radius: 20px;
  border: none;
  color: #0000ff;
  letter-spacing: 1px;
  background: white;
  font-size: 18px;
  margin-left: 10px;
}
.input::placeholder {
  color: #0000ff;
}
.save .file label{
  margin: 0 5px;
  font-size: 15px;
  display: flex;
  cursor: pointer;
}
.save .file label:hover{
  color: grey;
}
.features {
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
}
.mode {
  width: 80px;
  background-color: white;
  height: 30px;
  border-radius: 10px;
  display: flex;
  padding: 5px;
  justify-content: space-evenly;
  cursor: pointer;
}
.mode .pinkmode {
  width: 20px;
  height: 20px;
  background: pink;
  border-radius: 50%;
  display: block;
}
.mode .nopinkmode {
  width: 20px;
  height: 20px;
  background: #4680FF;
  border-radius: 50%;
  display: block;
}
.setpinkmode {
  background-image: linear-gradient(90deg,rgb(255, 211, 218), pink);
}
.saveimg{
  margin-right: 20px;
  cursor: pointer;
  color: white;
  font-weight: bold;
}
.saveimg:hover{
  color:#eee;
  transform: scale(1.1);
  
}
</style>