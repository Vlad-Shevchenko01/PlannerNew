package com.android.dayplanner.app.screens

import android.view.View
import com.android.dayplanner.app.R
import io.github.kakaocup.kakao.check.KCheckBox
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

interface ListOfTasksBasic {

    class RecyclerOfTasks(parent: Matcher<View>) : KRecyclerItem<RecyclerOfTasks>(parent) {
        val titleTextField = KTextView(parent) { withId(R.id.textView_title) }
        val descriptionTextField = KTextView(parent) { withId(R.id.textView_description) }
        val dateTextField = KTextView(parent) { withId(R.id.textView_date) }
        val deleteTaskButton = KImageView(parent) { withId(R.id.imageView_delete) }
        val completeTaskCheckBox = KCheckBox(parent) { withId(R.id.checkBox) }
    }

    fun actionClickOnCheckBox(recyclerView: KRecyclerView, textTitle: String) {
        recyclerView {
            childWith<RecyclerOfTasks> {
                withDescendant { withText(textTitle) }
            } perform {
                completeTaskCheckBox.click()
            }
        }
    }

    fun actionClickONDeleteButton(recyclerView: KRecyclerView, textTitle: String) {
        recyclerView {
            childWith<RecyclerOfTasks> {
                withDescendant { withText(textTitle) }
            } perform {
                deleteTaskButton.click()
            }
        }
    }

    fun actionClickOnTheTask(recyclerView: KRecyclerView, textTitle: String) {
        recyclerView {
            childWith<RecyclerOfTasks> {
                withDescendant { withText(textTitle) }
            } perform {
                titleTextField.click()
            }
        }
    }

    fun assertTheTaskIsNotInTheList(recyclerView: KRecyclerView, textTitle: String) {
        recyclerView {
            childWith<RecyclerOfTasks> {
                withDescendant { withText(textTitle) }
            } perform {
                doesNotExist()
            }
        }
    }

    fun assertTheTaskIsInTheList(
        recyclerView: KRecyclerView,
        textTitle: String,
        textDescription: String,
        date: String
    ) {
        recyclerView {
            childWith<RecyclerOfTasks> {
                withDescendant { withText(textTitle) }
            } perform {
                descriptionTextField.hasText(textDescription)
                dateTextField.hasText(date)
            }
        }
    }
}