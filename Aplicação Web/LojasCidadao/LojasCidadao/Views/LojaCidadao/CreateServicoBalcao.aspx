<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LojasCidadao.Models.RelacaoBalcaoServico>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Adicionar Serviço a Balcão
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Adicionar Serviço a Balcão</h2>

<script src="<%: Url.Content("~/Scripts/jquery.validate.min.js") %>" type="text/javascript"></script>
<script src="<%: Url.Content("~/Scripts/jquery.validate.unobtrusive.min.js") %>" type="text/javascript"></script>

<% using (Html.BeginForm()) { %>
    <fieldset>
        <legend>Adicionar Serviço</legend>

         <p>
            <label for="Loja">Loja ID:</label>
            <%= Html.TextBox("Loja", ViewData["LojaID"], new { @readonly="readonly" })%>
            <%= Html.ValidationMessage("Loja", "Loja incorreta") %>
        </p>
        <p>
            <label for="Entidade">Entidade ID:</label>
            <%= Html.TextBox("Entidade", ViewData["EntidadeID"], new { @readonly="readonly" })%>
            <%= Html.ValidationMessage("Entidade", "Entidade incorreta") %>
        </p>
        <p>
            <label for="Servico">Serviço:</label>
            <%= Html.DropDownList("Servico", ViewData["Servicos"] as SelectList) %>
            <%= Html.ValidationMessage("Serviço", "Serviço incorreto") %>
        </p>
        <p>
            <label for="Estado">Estado (CheckBox selecionada para ativar serviço):</label>
            <%= Html.CheckBox("Estado", Model.isEstado()) %>
        </p>
        <p>
            <input type="submit" value="Guardar" />
        </p>
    </fieldset>
<% } %>

<div>
    <%: Html.ActionLink("Voltar à Lista", "ServicosBalcao", new { id = ViewData["LojaID"].ToString()+"-"+ViewData["EntidadeID"].ToString() })%>
</div>

</asp:Content>
