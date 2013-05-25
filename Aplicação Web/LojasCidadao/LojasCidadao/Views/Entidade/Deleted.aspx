<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Apagado
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Apagado!</h2>
<div>
    <p>Apagou com sucesso a entidade.</p>
</div>
<div>
    <p><a href="/entidade">Voltar à lista</a></p>
</div>

</asp:Content>
