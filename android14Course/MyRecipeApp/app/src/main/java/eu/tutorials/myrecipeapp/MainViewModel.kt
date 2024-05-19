package eu.tutorials.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// dau hai cham la ke thua tuc la MainViewModel la lop con cua ViewModel
class MainViewModel:ViewModel() {
    private val _categoriesState= mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> =_categoriesState
    init{
        fetchCategories()
    }
    private fun fetchCategories(){
        //cho phep chay che do nen
        viewModelScope.launch {
            try{
                val response=recipeService.getCategories()
                _categoriesState.value=_categoriesState.value.copy(
                    list=response.categories,
                    loading =false,
                    error =null
                )
            }catch(e: Exception){
                _categoriesState.value=_categoriesState.value.copy(
                    loading = false,
                    error = "Error fetching Caetegories ${e.message}"
                )
            }
         }
    }

    data class RecipeState(
        val loading: Boolean =true,
        val list: List<Category> = emptyList(),
        val error: String? =null
    )
}