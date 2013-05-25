<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LojasCidadao.Models.LojaCidadao>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Mapa
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Mapa</h2>

<script type="text/javascript" src="http://js.sapo.pt/Bundles/SAPOMapsAPI.js"></script>
		
<div id='map' style='width:600px;height:450px'></div> 
<script type='text/javascript' onload="init(<%=Model.getLongitude() %>, <%=Model.getLatitude() %>);">
    var map, marker;

    function init(lon, lat) {
        map = new SAPO.Maps.Map('map');

        var marker = new SAPO.Maps.Marker(new OpenLayers.LonLat(lon, lat), { draggable: false },
		{ markerImage: 'http://4.bp.blogspot.com/-HMDE-gjQ-0Y/Tyzs6w7Ll2I/AAAAAAAAAWc/W4ouGELVklE/s1600/Map_pin3.png', size: new OpenLayers.Size(32, 43) });
        map.addOverlay(marker);
        map.setMapCenter(new OpenLayers.LonLat(lon, lat), 16);
        marker.registerEvent('click', this, doSomething);
    }
    function doSomething(marker) {
        marker.openPopup('<%=Html.Encode(Model.getNome()) %>');
    }
</script>

<div>
    <%: Html.ActionLink("Voltar à Lista", "Index") %>
</div>

</asp:Content>
