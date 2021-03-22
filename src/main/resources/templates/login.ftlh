<!DOCTYPE html>
<html>
    <head>
        <title>Archivos</title>
        <meta charset="UTF-8">
        <!-- Estas funcionan pero tienen problemas también con CORS-->
        <!-- Las demás hojas no funcionan -->
        <link href="https://staticv2.mineduc.cl/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="https://staticv2.mineduc.cl/css/mineduc-custom.css" rel="stylesheet" media="screen">
    </head>
    <body>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="userinfo">Conectado desde la dirección
                    IP:& n b s p ; $ { i p } & n b s p ; </div> <!-- Juntar caracteres -->
            </div>
        </div>

        <br>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div
                    class="container-fluid content with-outline rounded-top rounded-bottom">
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <h3 class="font-gob">Inicio de Sesión</h3>
                        </div>
                    </div>
                    <br>
                    <form role="form" id="form" method=post action="/login">

                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <label for="form_name">Nombre de Usuario:</label>
                                <input type="text" class="form-control" name="txtUsuario" id="form_name" placeholder="Usuario" value="">
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <label for="form_passw">Contraseña:</label>
                                <input type="password" class="form-control" name="txtClave" id="form_passw" placeholder="Contraseña" value="">
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <p>
                                    <input id="remember_session" name="remember_session" type="checkbox" /><span>&nbsp;No cerrar sesión</span>
                                </p>

                                [#if ((error)!-1) == 1] <br />
                                <div id="warning" class="alert alert-danger alert-dismissible" role="alert" >
                                    <span class="glyphicon glyphicon-exclamation-sign"></span>
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>&nbsp;
                                    <p>El nombre de usuario o la contraseña introducidos no son correctos.</p>
                                </div>
                                [/#if]

                                <p> <!-- Boton es tipo button, se controla con jquery el submit -->
                                    <button type="submit" id="ingresar" class="btn btn-primary-gradient">
                                        <span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;<span class="font-gob">Ingresar</span>
                                    </button>
                                </p>
                                <p>
                                    <a href="#">¿Olvido su Contraseña?</a>
                                </p>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <br>
        <br>
    </body>
</html>