from rest_framework import serializers

from books.models import Book, Author


class AuthorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Author
        fields = ["first_name", "last_name"]


class BookSerializer(serializers.ModelSerializer):
    author_first_name = serializers.CharField(
        source="author.first_name", read_only=True
    )

    class Meta:
        model = Book
        fields = ["name", "description", "author", "author_first_name"]
