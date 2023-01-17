<!DOCTYPE html>

<html>
<head>
    <style>
        div.gallery {
            margin: 5px;
            border: 1px solid #ccc;
            float: left;
            width: 180px;
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
            word-break: break-all;
        }

        .clearfix:after {
            content: "";
            display: table;
            clear: both;
        }
    </style>
</head>

<body>
    <h1>List of movies</h1>
    <a href="${adminPanelNavigationPathKey}">${backToAdminPanelKey}</a>
    <br>
    <form method="post">
        <#list movieListModelKey as movie>
            <div class="gallery">
                <a href="/cinema/movies/${movie.id}/chat">
                    <img src="${moviePosterBaseUrlKey}/${movie.posterId}" alt="poster for movie ${movie.title}" width="600" height="400">
                </a>
                <div class="desc">${movie.title}</div>
                <div class="desc">year: ${movie.yearCreated}</div>
                <div class="desc">age restriction: ${movie.ageRestriction}</div>
                <div class="desc">${movie.description}</div>
                <div class="desc"><input type="checkbox" name="movieIdList" value="${movie.title}"></div>
            </div>
        </#list>

        <div class="clearfix"></div>

        <div style="padding:6px;">
            <p><input type="submit" value="Delete selected movies"></p>
        </div>
    </form>
</body>
</html>