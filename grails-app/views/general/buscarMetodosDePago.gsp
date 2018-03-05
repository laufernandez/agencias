<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Ingresar Direccion</title>
</head>
<g:form action="buscarAgencias">
    Direccion:
    <g:field type="text" class="form-control" name="direccion" required=""/><br>
    Radio (Mts.):
    <g:field type="number" class="form-control" name="radio" required=""/><br>
    Metodo de Pago:
    <g:select name="metodo" class="form-control" from="${metodos}" optionKey="id"
                                optionValue="name" noSelection="['':'Elige el Metodo']" required="">
    </g:select>
    <br>
    <g:submitButton name="submit" class="btn btn-primary" value="Buscar Agencia"/>

</g:form>