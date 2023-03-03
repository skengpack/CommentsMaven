<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Comment service</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <h1 style="color: #0d6efd">Please leave your comment!</h1>
    <br>

    <!--Input form-->
    <form class="row g-3 needs-validation" novalidate method="post">
        <div class="row mb-3">
            <label for="name" class="col-sm-2 col-form-label">Name</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="name" placeholder="Your name" required name="name">
            </div>
            <div class="invalid-feedback">
                Please enter your name!
            </div>

            <label for="rating" class="col-sm-1 col-form-label">Rating</label>
            <div class="col-sm-4">
                <select class="form-select" id="rating" required name="rating">
                    <option selected disabled hidden value="">Choose rating</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>
            <div class="invalid-feedback">
                Please select a rating!
            </div>
        </div>

        <div class="row mb-3">
            <label for="comment" class="col-sm-2 col-form-label">Comment</label>
            <div class="col-sm-10">
                <textarea class="form-control" id="comment" placeholder="Your comment" rows="5" required
                          name="comment"></textarea>
            </div>
            <div class="invalid-feedback">
                Please enter a comment!
            </div>
        </div>

        <div class="col-12">
            <button class="btn btn-primary" type="submit">Send comment</button>
        </div>
    </form>

    <br>
    <br>
    <br>

    <!--Display form-->
    <#if comments?has_content>
        <ol class="list-group list-group-numbered">
            <#list comments as id, commentObject>
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold">${commentObject.name}</div>
                        ${commentObject.comment}
                    </div>
                    <span class="badge bg-primary rounded-pill">${commentObject.rating}</span>
                </li>
            </#list>
        </ol>

        <!--Pagination-->
        <br>
        <nav aria-label="...">
            <ul class="pagination justify-content-center">
                <#list pagination as page>
                    <li class="${page.visibilityParameter}">
                        <a class="page-link" href="${page.pageLink}">${page.pageName}</a>
                    </li>
                </#list>
            </ul>
        </nav>

        <#if pagingError>
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <strong>Attention!</strong> You have entered a page number that does not exist.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </#if>
    <#else>
        No comments yet!
    </#if>
</div>

<script src="js/bootstrap.min.js"></script>
<script src="js/javaScript.js"></script>
</body>
</html>