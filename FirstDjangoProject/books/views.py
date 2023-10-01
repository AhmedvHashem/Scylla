from django.core.exceptions import ObjectDoesNotExist
from rest_framework import viewsets
from rest_framework.decorators import action
from rest_framework.response import Response

from books.models import Book, Author
from books.serializers import BookSerializer, AuthorSerializer


def get_book_or_none(pk=None):
    try:
        return Book.objects.filter(pk=pk).first()
    except ObjectDoesNotExist:
        return None


class AuthorViewSet(viewsets.ModelViewSet):
    queryset = Author.objects.all()
    serializer_class = AuthorSerializer


class BookViewSet(viewsets.ModelViewSet):
    queryset = Book.objects.all()
    serializer_class = BookSerializer

    @action(detail=False, methods=["get"], url_path="test_get")
    def test_get(self, request):
        print("request.user")
        print(request.user)
        print("request.user")
        books = Book.objects.filter(author__user_id=request.user.id)
        serializer = BookSerializer(
            books,
            many=True,
        )
        return Response(serializer.data)

    @action(detail=False, methods=["post"], url_path="update")
    def list_update(self, request):
        books_data = request.data

        serializer = BookSerializer(
            data=books_data,
            many=True,
        )
        if serializer.is_valid(raise_exception=False):
            serializer.save()
            return Response(
                serializer.data,
            )
        else:
            return Response(
                serializer.errors,
                status=400,
            )
