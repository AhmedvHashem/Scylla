from django.urls import path

from members import views

urlpatterns = [
    path("members/", views.members, name="members"),
    path("members/<int:id>/", views.details, name="details"),
]
