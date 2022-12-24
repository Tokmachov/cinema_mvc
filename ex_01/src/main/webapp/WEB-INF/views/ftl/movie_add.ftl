<html>
    <head>
    </head>
    <body>
        <h1>Add movie</h1>
        <a href="${adminPanelNavigationPathKey}">${backToAdminPanelKey}</a>
        <form method="post" enctype="multipart/form-data">
            <label for="title">Title:</label><br>
            <input type="text" id="title" name="title"><br>

            <label for="year">Year:</label><br>
            <input type="number" id="year" name="year"><br>

            <label for="ageRestriction">Age restriction:</label><br>
            <input type="number" id="ageRestriction" name="ageRestriction"><br>

            <label for="description">Description</label><br>
            <textarea rows="5" cols="50" id="description" name="description"></textarea><br>

            <label for="poster">Select a poster:</label>
            <input type="file" id="poster" name="poster"><br><br>

            <input type="submit" value="add">
        </form>
    </body>
</html>