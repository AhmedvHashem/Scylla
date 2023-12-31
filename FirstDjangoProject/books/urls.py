from rest_framework import routers

from books.views import AuthorViewSet, BookViewSet

router = routers.DefaultRouter()
router.register(r"authors", AuthorViewSet, basename="authors")
router.register(r"books", BookViewSet, basename="books")

urlpatterns = router.urls
