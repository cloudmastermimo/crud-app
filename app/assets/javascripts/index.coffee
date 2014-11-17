$ ->
  $.get "/persons", (persons) ->
    $.each persons, (index, person) ->
      $("#persons").append $("<li>").text person.name
      $("#persons").append '<form action="/person/' + person.id + '/delete" method="POST"><input type="submit" value="Delete"></form>'
