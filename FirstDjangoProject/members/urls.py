from django.urls import path

from members import views

urlpatterns = [
    path("members/", views.members, name="members"),
    path("members/<slug:slug>/", views.details, name="details"),
]
