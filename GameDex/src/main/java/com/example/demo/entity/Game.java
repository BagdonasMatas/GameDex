package com.example.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="games")
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name="name")
	@NotBlank(message ="Name is required")
	private String name;
	
	@Column(name="genre")
	@NotBlank(message ="Genre is required")
	private String genre;
	
	@Column(name="average_rating")
	private Float averageRating;
	
	@ManyToOne
	@JoinColumn(name = "developer_id")
	private Developer developer;
	
	@ManyToMany(mappedBy = "userGameFavorites")
	private List<User> gameUserFavorites;
}
