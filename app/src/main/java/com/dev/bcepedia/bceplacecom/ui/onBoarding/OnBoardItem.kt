package com.dev.bcepedia.bceplacecom.ui.onBoarding

class OnBoardItem(
  private var imageId: Int, private var title: String, private var desc: String) {

  fun getImageId(): Int {
    return this.imageId
  }

  fun setImageId(imageId: Int): Unit{
    this.imageId = imageId
  }

  fun getTitle(): String{
    return this.title
  }

  fun setTitle(title: String): Unit{
    this.title = title
  }

  fun getDescription(): String{
    return this.desc
  }

  fun setDescription(desc: String){
    this.desc = desc
  }

}