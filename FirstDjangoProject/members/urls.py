from django.urls import path

from members import views

urlpatterns = [
    path("members/", views.members, name="members"),
]
