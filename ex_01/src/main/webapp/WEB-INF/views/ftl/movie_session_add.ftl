<html>
    <head>
    </head>
    <body>
        <h1>${sessionAddTitleKey}</h1>
        <a href=${adminPanelNavigationPathKey}>${backToAdminPanelKey}</a>
        <form method="post">
            <label for="dateInput">Time:</label><br>
            <input type="datetime-local" name="dateAndTime" id="dateInput"><br>

            <label for="movieIds">Movies:</label><br>
            <select id="Movies" name="movieTitle">
                <#list movieListModelKey as movie>
                    <option value="${movie.title}">${movie.title}</option>
                </#list>
            </select><br>

            <label for="MovieHalls">Movie halls:</label><br>
            <select id="MovieHalls" name="movieHallId">
                <#list movieHallsListModelKey as movieHall>
                    <option value="${movieHall.id}">${movieHall.id}</option>
                </#list>
            </select><br>

            <input type="submit" value="Add session">

        </form>
    </body>
</html>