URL ="jogo";

var Game = function() {
    this.c = new Coquette(this,'canvas',800,800,"#fff");
    this.carregador = new CarregadorRecursos(this.c);
    this.dragger = new Dragger(this.c);
    this.carregador.carregar(function(c) {
        var myLettrere = c.entities.create(Palavra,{center : { x: 300, y: 300},word: "test"});
    });
    var myblock  = this.c.entities.create(Imagem,{ center: { x: 100, y: 101 }, size: { x: 154, y: 271 }, destination: "imagens/mariosprite.jpg" });
    console.log(this);
}

Game.prototype = {
  update : function() {
    this.dragger.update();
  }
}



var Dragger = function(c) {
  this.c = c;
    this._currentDrag;
    var self = this;

    c.inputter.bindMouseMove(function(e) {
        if (c.inputter.isDown(c.inputter.LEFT_MOUSE)) {
          if (self._isDragging()) {
              self._currentDrag.target.center = {
                x: e.x + self._currentDrag.centerOffset.x,
                y: e.y + self._currentDrag.centerOffset.y
              };
          }
        }
    });
};

var Palavra = function(game, settings) {
  this.game = game;
  this.center = settings.center;
  this.size = {x: 0 , y: 30};
  this.word = settings.word;
  console.log("criando palavra");
  console.log(game);

}

Palavra.prototype = {
  update : function(tick) {
  },
  draw : function(ctx) {
    //console.log(ctx.measureText("Texto Teste"));
    //console.log(Math.round(ctx.measureText("Texto Teste").width));
    ctx.font = "Georgia";
    ctx.fillStyle = "black";
    //this.size['x'] = Math.round(ctx.measureText("Texto Teste").width);
    ctx.fillText(this.word, this.center.x,this.center.y);
  },
  // startDrag: function() {
  //   this.vec = { x: 0, y: 0 };
  // },
  collision: function(other) {
    console.log("bateu na letra");

  }
  
}

var Imagem = function(game, settings) {
  this.game = game;
  this.center = settings.center;
  this.size = settings.size;
  this.angle = 0;
  this.imagem = new Image();
  this.imagem.src = settings.destination;
};

Imagem.prototype = {
  update: function(timeSinceLastTick) {
  },

  draw: function(ctx) {
    ctx.drawImage(this.imagem,this.center.x - this.size.x / 2,this.center.y - this.size.y / 2);
    //console.log("desenhado");
  },
  startDrag: function() {
    this.vec = { x: 0, y: 0 };
  },
  collision: function(other) {
    console.log(other);
  }
};

Dragger.prototype = {

    update: function() {
        if (this.c.inputter.isDown(this.c.inputter.LEFT_MOUSE)) {
          if (!this._isDragging()) {
              var mousePosition = this.c.inputter.getMousePosition();
              var target = this._getTarget(this.c.entities.all(), mousePosition);
              if (target !== undefined) {
                this._startDrag(target, mousePosition);
              }
          }
        } else {
          this._stopDrag();
        }
    },
    _isDragging: function() {
      return this._currentDrag !== undefined;
    },

    _getTarget: function(targets, e) {
        for (var i = 0; i < targets.length; i++) {
          if (Coquette.Collider.Maths.pointInsideObj(e, targets[i])) {
              return targets[i];
          }
        }
    },

    _startDrag: function(target, e) {
        this._currentDrag = {
          target: target,
          centerOffset: {
              x: target.center.x - e.x,
              y: target.center.y - e.y
          }
        };
        if (target.startDrag !== undefined) {
          target.startDrag();
        }
    },

    _stopDrag: function() {
        if (this._isDragging()) {
          if (this._currentDrag.target.stopDrag !== undefined) {
              this._currentDrag.target.stopDrag();
          }
          this._currentDrag = undefined;
        }
    }
};

var CarregadorRecursos = function(c) {
    this.c = c;
    this.recursos = [];
}

CarregadorRecursos.prototype = {
    carregar : function(callback) {
        $.ajax({
            url : (URL+"?Typedata=imagens"),
            method : "GET",
            complete : function(obj) {
                this.recursos.push(obj.responseText);
            },
            async: true
        }).done(callback(this.c));
    }
}

window.addEventListener('load',function() {
    new Game();
})
