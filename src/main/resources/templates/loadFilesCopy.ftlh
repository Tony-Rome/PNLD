<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <title>Archivos</title>
    <meta charset="UTF-8">
    <link href="css/style.css" rel="stylesheet"/>
    <script>

        var dropFile;

        function allowDrop(ev) {
          ev.preventDefault();
          console.log("function allowDrop");
        }

        function drag(ev) {
          ev.dataTransfer.setData("text", ev.target.id);
        }

        function drop(ev) {
          ev.preventDefault();
          console.log(ev.dataTransfer);
          if(ev.dataTransfer.items){
            console.log("Entro a dataTransfer.items");
            console.log(ev.dataTransfer.items);
            for(var i = 0; i < ev.dataTransfer.items.length; i++){
                var file = ev.dataTransfer.items[i].getAsFile();
                console.log('... file[' + i + '].name = ' + file.name);
                var fd = new FormData();
                fd.append('csvFile', file);
                console.log("Este es fd");
                $.ajax({
                    type: 'POST',
                    url: '/scheduleLoadFilePost',
                    contentType: false,
                    processData: false,
                    data: fd,
                    success: function(response){
                        alert(response);
                    }
                })
            }
          }
          else{
            for (var i = 0; i < ev.dataTransfer.files.length; i++) {
                  console.log('... file[' + i + '].name = ' + ev.dataTransfer.files[i].name);
                }
          }
        }

        </script>
</head>
<body>
    <div class="banner">
        <div class="logo_mineduc">Imagen</div>
        <div class="titulo">Archivos</div>

    </div>
<#if csvFile??>
    Archivo Ingresado<br>
    Nombre: ${csvFile.name}<br>
    Tipo: ${csvFile.selectedType}<br>
<#else>
    <form action="/scheduleLoadFilePost" method="post" enctype="multipart/form-data">
        Nombre:<br>
        <input type="text" name="name"/>
        <br><br>
        Tipo:<br>
        <select name="selectedType" id="selectedType">
            <option value="diag">diagnostico</option>
            <option value="pre-cap">pre-capacitacion</option>
            <option value="post-cap">post-capacitacion</option>
            <option value="pc-1">PC 1 a 4 año</option>
            <option value="pc-2">PC 5 a 8 año</option>
            <option value="pc-3">PC docentes</option>
            <option value="salida">salida</option>
            <option value="satis">satisfaccion</option>
        </select>
        <br><br>
        Buscar Archivo:<br>
        <textarea id="subject" name="subject" placeholder="Arrastre y suelte el archivo nuevo..." style="height:200px"></textarea>
        <input type="file" id="uploadFile" name="uploadFile" >
        <br><br>
        <input type="submit" value="Iniciar Carga">
    </form>
</#if>
</body>
</html>