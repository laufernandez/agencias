<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Agencias</title>
</head>
<table class='display'>
    <tr>
        <th>Agencia</th>
        <th>Distancia</th>
        <th>Info</th>

    </tr>
<g:each in="${modelo.agencias}" var="agencia" status="i">
    <div class="modal fade" id='modalAgencia${i}' role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">${agencia.descripcion}</h4>
                </div>
                <div class="modal-body">
                    <p>Direccion: ${agencia.direccion}</p>
                    <p>Ciudad: ${agencia.ciudad}</p>
                    <p>Provincia: ${agencia.estado}</p>
                    <p>Pais: ${agencia.pais}</p>
                    <p>Distancia: <g:formatNumber number="${agencia.distancia} type="number" maxFractionDigits="1" roundingMode="CEILING" /> metros</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>
    <tr>
        <td>
            <p>${agencia.descripcion}</p>
        </td>
        <td>
            <p><g:formatNumber number="${agencia.distancia} type="number" maxFractionDigits="1" roundingMode="CEILING" /> metros</p>
        </td>
        <td>
            <p><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalAgencia${i}">Ver más</button></p></td>
    </tr>

</g:each>
</table>

    <title>Simple Map</title>
    <meta name="viewport" content="initial-scale=1.0">
    <meta charset="utf-8">
    <style>
    /* Always set the map height explicitly to define the size of the div
     * element that contains the map. */
    #map {
        height: 50%;
    }
    /* Optional: Makes the sample page fill the window. */
    html, body {
        height: 100%;
        margin: 0;
        padding: 0;
    }
    </style>

<div id="map"></div>
<script>
    var map;
    var marcadoresAgencias = [];
    var coordenadas = {lat: ${modelo.latitud}, lng: ${modelo.longitud}};
    var latitud;
    var longitud;
    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: coordenadas,
            zoom: 15
        });
        var direccion = new google.maps.Marker({
            position: coordenadas,
            map: map,
            title: "Tu Direccion",
            icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png'
        });
        direccion.setAnimation(google.maps.Animation.BOUNCE);


        <g:each var="agencia" in="${modelo.agencias}">
            latitud = ${agencia.latitud}
            longitud = ${agencia.longitud}
            marcadoresAgencias.push(new google.maps.Marker({
                position: {lat: latitud, lng: longitud},
                map: map
            }));
        </g:each>
    }
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDBUi7d-3nMSohL1zt7k1FUpS0rP_JkVIk&callback=initMap"
        async defer></script>