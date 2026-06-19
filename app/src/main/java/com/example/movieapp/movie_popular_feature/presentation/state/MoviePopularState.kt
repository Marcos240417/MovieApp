package com.example.movieapp.movie_popular_feature.presentation.state

import androidx.paging.PagingData
import com.example.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

data class MoviePopularState(
   val movies: Flow<PagingData<Movie>>
)
//Sua classe MoviePopularState utiliza um Flow<PagingData<Movie>> para gerenciar
//a lista de filmes populares que serão exibidos na interface do usuário. Essa abordagem
//permite que o seu ViewModel se conecte diretamente ao repositório que criamos,
//facilitando a atualização automática da tela sempre que novos filmes forem carregados da API.