from django.db import models
from django.contrib.auth.models import User


class Author(models.Model):
    user = models.ForeignKey(
        User,
        on_delete=models.CASCADE,
        null=True,
    )
    first_name = models.CharField(max_length=255)
    last_name = models.CharField(max_length=255)

    def __str__(self):
        return f"{self.first_name}-{self.last_name}"


class Book(models.Model):
    name = models.CharField(max_length=255)
    description = models.TextField(blank=True, null=True)

    author = models.ForeignKey(
        Author,
        on_delete=models.CASCADE,
        related_name="books",
    )

    def __str__(self):
        return f"{self.name}"
