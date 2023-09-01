from django.db import models
from django.template.defaultfilters import slugify


class Member(models.Model):
    slug = models.SlugField(
        max_length=255,
        unique=True,
    )
    first_name = models.CharField(max_length=255)
    last_name = models.CharField(max_length=255)

    def save(self, *args, **kwargs):
        if not self.slug:
            new_slug = f"{self.first_name}-{self.last_name}"
            self.slug = slugify(new_slug)
        return super().save(*args, **kwargs)
