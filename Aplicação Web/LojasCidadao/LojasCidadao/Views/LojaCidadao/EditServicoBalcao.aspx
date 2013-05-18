<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LojasCidadao.Models.RelacaoBalcaoServico>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Editar Serviço Balcão
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Editar Serviço de Balcão</h2>

<script src="<%: Url.Content("~/Scripts/jquery.validate.min.js") %>" type="text/javascript"></script>
<script src="<%: Url.Content("~/Scripts/jquery.validate.unobtrusive.min.js") %>" type="text/javascript"></script>

<% using (Html.BeginForm()) { %>
    <fieldset>
        <legend>Editar Serviço de Balcão</legend>

        <p>
            <label for="Estado">Estado (CheckBox selecionada para ativar balcão):</label>
            <%= Html.CheckBox("Estado", Model.isEstado()) %>
        </p>
        <p>
            <input type="submit" value="Guardar" />
        </p>
    </fieldset>
<% } %>

<div>
    <%: Html.ActionLink("Voltar atrás", "ServicosBalcao", new { id = ViewData["Loja"].ToString()+"-"+ViewData["Entidade"].ToString() })%>
</div>

</asp:Content>
