<!DOCTYPE html>
<html>
<head>
    <style>
        div.gallery {
            margin: 5px;
            border: 1px solid #ccc;
            float: left;
            width: 300px;
        }

        div.gallery:hover {
            border: 1px solid #777;
        }

        div.gallery img {
            width: 100%;
            height: auto;
        }

        div.desc {
            padding: 15px;
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>List of movies</h1>
    <a href="${adminPanelNavigationPathKey}">${backToAdminPanelKey}</a>
    <br>
    <#list movieListModelKey as movie>
        <div class="gallery">
            <a target="_blank" href="movies/poster/${movie.posterId}">
                <img src="movies/poster/${movie.posterId}" alt="poster for movie ${movie.title}" width="3000" height="3000">
            </a>
            <div class="desc">${movie.description}</div>
        </div>
    </#list>


</body>
</html>