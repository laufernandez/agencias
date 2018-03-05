<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Buscar Agencias</title>
</head>
<body>
<h1>Buscar Agencias de Pago para MercadoLibre</h1>
        <div id="content" role="main">
        <g:form action="buscarMetodosDePago">

            <g:select class="form-control" name="sitio" from="${sitios}" optionKey="id"
            optionValue="name" noSelection="['':'Elige Sitio']">
            </g:select>
            <br>
            <g:submitButton class="btn btn-primary" name="submit" value="Ok"/>
        </g:form>
        </div>


</body>
</html>